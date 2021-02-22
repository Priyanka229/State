package apptentive.com.states.base

import android.widget.Toast
import androidx.fragment.app.Fragment


open class BaseApptentiveFragment: Fragment() {
    protected fun isAlive() = activity != null && activity?.isFinishing == false

    protected fun showMsg(msg: String) {
        if (isAlive().not()) return
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
    }
}