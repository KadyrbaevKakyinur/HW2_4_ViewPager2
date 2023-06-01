package com.example.hw2_4_viewpager2.contacts

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.ContactsContract
import android.provider.ContactsContract.CommonDataKinds.Phone
import android.provider.ContactsContract.Contacts
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.hw2_4_viewpager2.board.BaseFragment
import com.example.hw2_4_viewpager2.databinding.FragmentContactsBinding


class ContactNoteFragment : BaseFragment<FragmentContactsBinding>(FragmentContactsBinding::inflate) {
    private val adapter: ContactAdapter by lazy { ContactAdapter(this::call,this::chat) }
    private lateinit var phoneNumber: String

    override fun setupUI() {
        Toast.makeText(requireContext(), "Your contacts", Toast.LENGTH_LONG).show()
        binding.recyclerView.adapter = adapter
        if (ActivityCompat.checkSelfPermission(
                requireContext(), android.Manifest.permission.READ_CONTACTS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(requireActivity(), Array(1) {
                android.Manifest.permission.READ_CONTACTS
            }, 111)
        } else {
            getcontact()
        }

    }

    @SuppressLint("Range", "Recycle")
    private fun getcontact() {
        val list = arrayListOf<ContactModel>()
        val contentResolver = requireActivity().contentResolver
        val cursor = contentResolver.query(
            Contacts.CONTENT_URI, null, null, null,
            Phone.DISPLAY_NAME
        )
        if (cursor?.count!! > 0) {
            while (cursor.moveToNext()) {
                if (Integer.parseInt(
                        cursor.getString(
                            cursor.getColumnIndex(
                                Contacts.HAS_PHONE_NUMBER
                            )
                        )
                    ) > 0
                ) {
                    val id = cursor.getString(cursor.getColumnIndex(Contacts._ID))
                    val name = cursor.getString(cursor.getColumnIndex(Contacts.DISPLAY_NAME))

                    val numberCursor = contentResolver.query(
                        Phone.CONTENT_URI,
                        null,
                        Phone.CONTACT_ID + " =?",
                        arrayOf(id),
                        null
                    )
                    if (numberCursor?.moveToNext()!!){
                         phoneNumber= numberCursor.getString(numberCursor.getColumnIndex(Phone.NUMBER))


                    }
                    val imageCursor = contentResolver.query(
                        ContactsContract.Data.CONTENT_URI,
                        null,
                        ContactsContract.Data.CONTACT_ID + "=?" + ContactsContract.Data.MIMETYPE + "=?",
                        arrayOf(id, ContactsContract.CommonDataKinds.Photo.CONTENT_ITEM_TYPE),
                        null
                    )

                    if (imageCursor?.moveToFirst() == true) {
                        val photoBytes = imageCursor.getBlob(imageCursor.getColumnIndex(ContactsContract.CommonDataKinds.Photo.PHOTO))
                        if (photoBytes != null) {
                            val bitmap = BitmapFactory.decodeByteArray(photoBytes, 0, photoBytes.size)
                            list.add(ContactModel(name = name, number = phoneNumber, img = bitmap))
                        }
                    }
                    imageCursor?.close()
                }
            }
            cursor.close()
            adapter.submitList(list)
        }
    }

    private fun call(number: String){
        if (ActivityCompat.checkSelfPermission(requireContext(),
                android.Manifest.permission.CALL_PHONE)==PackageManager.PERMISSION_GRANTED){
            callContact(number)
        }else{
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(android.Manifest.permission.CALL_PHONE),110)
        }
        val intent=Intent(Intent.ACTION_CALL)
        intent.data = Uri.parse("tel:$number")
        startActivity(intent)
    }

    private fun chat (number: String){
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("https://whatsapp.me/$number")
        startActivity(intent)
    }

    private fun callContact(number:String){
        val intent=Intent(Intent.ACTION_CALL)
        intent.data = Uri.parse("tel:$number")
        startActivity(intent)

    }

}