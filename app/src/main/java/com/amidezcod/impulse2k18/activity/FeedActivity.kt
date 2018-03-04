package com.amidezcod.impulse2k18.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import com.amidezcod.impulse2k18.adapter.Feedadapter
import com.amidezcod.impulse2k18.modal.FeedModal
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import impulse2k18.R
import kotlinx.android.synthetic.main.activity_feed.*
import java.util.*


class FeedActivity : AppCompatActivity() {

    lateinit var databaseReference: DatabaseReference
    lateinit var childEventListener: ChildEventListener
    lateinit var feedadapter: Feedadapter
    lateinit var chats: MutableList<FeedModal>
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firebaseAuthListener: FirebaseAuth.AuthStateListener
    private lateinit var firebaseStorage: FirebaseStorage
    lateinit var storageReference: StorageReference
    private val RC_SIGN_IN: Int = 123

    private val RC_PHOTO_PICKER: Int = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)
        if (FirebaseDatabase.getInstance() == null)
            FirebaseDatabase.getInstance().setPersistenceEnabled(true)


        if (FirebaseAuth.getInstance() != null) {
            firebaseAuth = FirebaseAuth.getInstance()
        }

        firebaseStorage = FirebaseStorage.getInstance()
        storageReference = firebaseStorage.reference
        progressBar.visibility = View.INVISIBLE

        databaseReference = FirebaseDatabase.getInstance().reference

        message_recycler_view.setHasFixedSize(true)
        message_recycler_view.layoutManager = LinearLayoutManager(this@FeedActivity)
        chats = ArrayList()
        feedadapter = Feedadapter(this@FeedActivity, chats)
        message_recycler_view.adapter = feedadapter

        messageEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                sendButton.isEnabled = s.toString().trim().isNotEmpty()
            }

        })

        sendButton.setOnClickListener({
            val feedModal = FeedModal(messageEditText.text.toString(), "")
            databaseReference.push().setValue(feedModal)
            messageEditText.setText("")
        })
        childEventListener = object : ChildEventListener {
            override fun onCancelled(p0: DatabaseError?) {
            }

            override fun onChildMoved(p0: DataSnapshot?, p1: String?) {
            }

            override fun onChildChanged(p0: DataSnapshot?, p1: String?) {
            }

            override fun onChildAdded(p0: DataSnapshot?, p1: String?) {
                val feedModal = p0?.getValue(FeedModal::class.java)
                chats.add(feedModal!!)
                message_recycler_view.scrollToPosition(chats.size - 1)
                feedadapter.notifyItemInserted(chats.size - 1)

            }

            override fun onChildRemoved(p0: DataSnapshot?) {
            }
        }
        databaseReference.addChildEventListener(childEventListener)

        firebaseAuthListener = FirebaseAuth.AuthStateListener { it: FirebaseAuth ->
            val firebaseUser: FirebaseUser? = it.currentUser
            if (firebaseUser != null) {
                Toast.makeText(this@FeedActivity, firebaseUser.displayName, Toast.LENGTH_LONG).show()
            } else {
                startActivityForResult(
                        AuthUI.getInstance()
                                .createSignInIntentBuilder()
                                .setIsSmartLockEnabled(false)
                                .setAvailableProviders(Arrays.asList(
                                        AuthUI.IdpConfig.GoogleBuilder().build()))
                                .build(),
                        RC_SIGN_IN)

            }
        }
        photoPickerButton.setOnClickListener({
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true)
            startActivityForResult(Intent.createChooser(intent, "Choose photo "), RC_PHOTO_PICKER)

        })

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == RC_PHOTO_PICKER && resultCode == Activity.RESULT_OK) {
            val uri = data?.data
            val reference = storageReference.child(uri!!.lastPathSegment)
            reference.putFile(uri).addOnSuccessListener(this@FeedActivity) { it: UploadTask.TaskSnapshot? ->
                databaseReference.push().setValue(FeedModal("", it?.downloadUrl.toString()))
            }
        }
    }


    override fun onResume() {
        super.onResume()
        firebaseAuth.addAuthStateListener(firebaseAuthListener)
    }

    override fun onPause() {
        super.onPause()
        firebaseAuth.removeAuthStateListener(firebaseAuthListener)
    }
}
