package fes.aragon.proyectofinal
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.OAuthProvider
import fes.aragon.proyectofinal.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var binding: ActivityMainBinding // data binding
    private val provider = OAuthProvider.newBuilder("github.com")




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()
        getPendingAuthResult()
        binding.btnGithub.setOnClickListener {
            signInWithProvider(provider)
        }
    }

    fun getPendingAuthResult() {
        // [START auth_oidc_pending_result]
        val pendingResultTask = firebaseAuth.pendingAuthResult
        if (pendingResultTask != null) {
            // There's something already here! Finish the sign-in for your user.
            pendingResultTask
                .addOnSuccessListener {
                    // User is signed in.
                    // IdP data available in
                    // authResult.getAdditionalUserInfo().getProfile().
                    // The OAuth access token can also be retrieved:
                    // ((OAuthCredential)authResult.getCredential()).getAccessToken().
                    // The OAuth secret can be retrieved by calling:
                    // ((OAuthCredential)authResult.getCredential()).getSecret().
                    Toast.makeText(this, "User is signed in", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    // Handle failure.
                    Toast.makeText(this, "User is not signed in", Toast.LENGTH_SHORT).show()
                }
        } else {
            // There's no pending result so you need to start the sign-in flow.
            // See below.
            Toast.makeText(this, "There's no pending result", Toast.LENGTH_SHORT).show()
        }
        // [END auth_oidc_pending_result]
    }
    fun signInWithProvider(provider: OAuthProvider.Builder) {
        // [START auth_oidc_provider_signin]
        firebaseAuth
            .startActivityForSignInWithProvider(this, provider.build())
            .addOnSuccessListener {
                // User is signed in.
                // IdP data available in
                // authResult.getAdditionalUserInfo().getProfile().
                // The OAuth access token can also be retrieved:
                // ((OAuthCredential)authResult.getCredential()).getAccessToken().
                // The OAuth secret can be retrieved by calling:
                // ((OAuthCredential)authResult.getCredential()).getSecret().
                Toast.makeText(this, "Usuario autenticado con Github", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, ContactsActivity::class.java)
                startActivity(intent)
                finish()
            }
            .addOnFailureListener {
                // Handle failure.
                Toast.makeText(this, "Autenticación fallida", Toast.LENGTH_SHORT).show()
            }
        // [END auth_oidc_provider_signin]
    }

}