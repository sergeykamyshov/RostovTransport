package ru.sergeykamyshov.rostovtransport.ui.complain

interface Contract {
    interface View {
        fun getTransportTypeTitle(): String
        fun getTransportTypeString(): String
        fun getRouteTitle(): String
        fun getRouteString(): String
        fun getTransportNumberTitle(): String
        fun getTransportNumberString(): String
        fun getTimeTitle(): String
        fun getTimeString(): String
        fun getPlaceTitle(): String
        fun getPlaceString(): String
        fun getViolationsTitle(): String
        fun getCheckedItems(): List<ViolationItem>
        fun getCommentTitle(): String
        fun getCommentString(): String
        fun showCheckErrorToast()
        fun sendComplaintViaEmail(text: String)
    }

    interface Presenter {
        fun attachView(view: Contract.View)
        fun viewIsReady()
        fun detachView()

        fun sendComplaint()
    }
}