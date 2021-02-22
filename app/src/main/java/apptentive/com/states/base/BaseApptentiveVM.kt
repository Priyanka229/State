package apptentive.com.states.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import apptentive.com.states.utilities.ApptentiveLogger
import apptentive.com.states.utilities.SingleLiveEvent
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers

abstract class BaseApptentiveVM<REPO: BaseApptentiveRepository, INTER: BaseApptentiveInteractor>(application: Application): AndroidViewModel(application) {
    lateinit var repository: REPO
    lateinit var interactor: INTER

    val uiBlockingLoaderLiveEvent = SingleLiveEvent<Boolean>() /** ui-blocking loader live-data */
    val showMsgLiveEvent = SingleLiveEvent<String>() /** msg live-data */

    protected val coroutineContext = Dispatchers.Default + CoroutineExceptionHandler{ _, th ->
        if (ApptentiveLogger.DEBUG) { th.printStackTrace() }
    }
}