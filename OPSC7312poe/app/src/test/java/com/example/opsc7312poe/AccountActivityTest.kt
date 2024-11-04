package com.example.opsc7312poe

import android.app.Activity
import android.content.Intent
import android.widget.TextView
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.firebase.auth.FirebaseAuth
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*

@RunWith(AndroidJUnit4::class)
class AccountActivityTest {

    private lateinit var authMock: FirebaseAuth

    @Before
    fun setUp() {
        authMock = mock(FirebaseAuth::class.java)
    }

    @Test
    fun testUserNameDisplay() {
        ActivityScenario.launch(AccountActivity::class.java).use { scenario ->
            scenario.onActivity { activity ->
                val userNameTextView = activity.findViewById<TextView>(R.id.userName)

                // Check if username displays correctly
                assertEquals("Victoria Robertson", userNameTextView.text.toString())
            }
        }
    }

    @Test
    fun testNavigationToEditProfile() {
        ActivityScenario.launch(AccountActivity::class.java).use { scenario ->
            scenario.onActivity { activity ->
                val editProfileIntent = Intent(activity, EditProfileActivity::class.java)

                activity.startActivityForResult(editProfileIntent, AccountActivity.EDIT_PROFILE_REQUEST_CODE)
                assertNotNull(editProfileIntent)
            }
        }
    }

    @Test
    fun testLogoutButtonFunctionality() {
        ActivityScenario.launch(AccountActivity::class.java).use { scenario ->
            scenario.onActivity { activity ->
                // Simulate clicking the logout button
                activity.logoutButton.performClick()

                // Verify that signOut was called on auth
                verify(authMock).signOut()

                // Check if the activity transitions to the Login activity
                val expectedIntent = Intent(activity, Login::class.java)
                assertNotNull(expectedIntent)
            }
        }
    }

    @Test
    fun testBackButtonFunctionality() {
        ActivityScenario.launch(AccountActivity::class.java).use { scenario ->
            scenario.onActivity { activity ->
                // Simulate clicking the back button
                activity.backButton.performClick()

                // Confirm that the activity is finished after clicking back
                assertTrue(activity.isFinishing)
            }
        }
    }

    @Test
    fun testOnActivityResultUpdatesUserName() {
        ActivityScenario.launch(AccountActivity::class.java).use { scenario ->
            scenario.onActivity { activity ->
                val updatedName = "Updated Name"
                val data = Intent().apply { putExtra("updatedName", updatedName) }

                activity.onActivityResult(AccountActivity.EDIT_PROFILE_REQUEST_CODE, Activity.RESULT_OK, data)

                // Verify the username is updated
                assertEquals(updatedName, activity.userNameTextView.text.toString())
            }
        }
    }
}
