package ru.surfstudio.summerschool.ui.main

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.surfstudio.summerschool.R
import ru.surfstudio.summerschool.app.data.SimilarContactsPair
import ru.surfstudio.summerschool.databinding.ActivityMainBinding
import ru.surfstudio.summerschool.ui.confirmation.ConfirmationActivity

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding by viewBinding(ActivityMainBinding::bind, R.id.activity_main_root)
    private val viewModel: MainViewModel by viewModels()

    /*
    ...
        // обработчик разрешения через Activity Result API
        private val contactsPermissionsLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission(),
            ::handlePermissions
        )
    */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnAction.setOnClickListener {
            if (arePermissionsGranted()) {
                goToConfirmation()
            } else {
                // запрашиваем разрешение на чтение контактов
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.READ_CONTACTS),
                    REQUEST_CODE_PERMISSIONS
                )
                /*
                ...
                // запрашиваем разрешение через Activity Result Api
                contactsPermissionsLauncher.launch(Manifest.permission.READ_CONTACTS)
                 */
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            handlePermission(grantResults.firstOrNull() == PackageManager.PERMISSION_GRANTED)
        }
    }

    private fun handlePermission(isGranted: Boolean?) {
        if (isGranted == true) {
            goToConfirmation()
        }
    }

    /** Получает контакт и переводит на экран [ConfirmationActivity] */
    private fun goToConfirmation() {
        val contactsInfo = viewModel.getContactsInfo(contentResolver)
        val groupedContacts = ArrayList<SimilarContactsPair>(contactsInfo)
        val intent = Intent(this, ConfirmationActivity::class.java)
            .putParcelableArrayListExtra(ConfirmationActivity.ARG_CONTACTS_LIST, groupedContacts)
        startActivity(intent)
    }

    /** Проверяет наличие разрешения на чтение контактов */
    private fun arePermissionsGranted(): Boolean = ContextCompat.checkSelfPermission(
        this, Manifest.permission.READ_CONTACTS
    ) == PackageManager.PERMISSION_GRANTED

    companion object {
        /** Код для определения типа запроса в [onRequestPermissionsResult] */
        private const val REQUEST_CODE_PERMISSIONS = 1
    }
}