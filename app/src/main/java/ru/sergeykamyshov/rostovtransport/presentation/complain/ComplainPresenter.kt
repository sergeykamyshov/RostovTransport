package ru.sergeykamyshov.rostovtransport.presentation.complain

class ComplainPresenter : Contract.Presenter {
    var view: Contract.View? = null

    override fun attachView(view: Contract.View) {
        this.view = view
    }

    override fun viewIsReady() {

    }

    override fun detachView() {
        view = null
    }

    override fun sendComplaint() {
        if (!isFillCorrect()) {
            view?.showCheckErrorToast()
            return
        }
        val text = buildComplainText()
        view?.sendComplaintViaEmail(text)
    }

    private fun isFillCorrect(): Boolean {
        val transportType = view?.getTransportTypeString()
        transportType?.let { if (it.isBlank()) return false }

        val route = view?.getRouteString()
        route?.let { if (it.isBlank()) return false }

        val transportNumber = view?.getTransportNumberString()
        transportNumber?.let { if (it.isBlank()) return false }

        val time = view?.getTimeString()
        time?.let { if (it.isBlank()) return false }

        val place = view?.getPlaceString()
        place?.let { if (it.isBlank()) return false }

        val items = view?.getCheckedItems()
        items?.let { if (it.isEmpty()) return false }

        return true
    }

    private fun buildComplainText(): String {
        var text = ""
        val transportType = view?.getTransportTypeString()
        transportType?.let { text += "${view?.getTransportTypeTitle()}: \n$it\n\n" }

        val route = view?.getRouteString()
        route?.let { text += "${view?.getRouteTitle()}: \n$it\n\n" }

        val transportNumber = view?.getTransportNumberString()
        transportNumber?.let { text += "${view?.getTransportNumberTitle()}: \n$it\n\n" }

        val time = view?.getTimeString()
        time?.let { text += "${view?.getTimeTitle()}: \n$it\n\n" }

        val place = view?.getPlaceString()
        place?.let { text += "${view?.getPlaceTitle()}: \n$it\n\n" }

        val items = view?.getCheckedItems()
        items?.let {
            text += "${view?.getViolationsTitle()}:\n"
            for (item in it) {
                text += "- ${item.name}\n"
            }
            text += "\n"
        }

        val comment = view?.getCommentString()
        comment?.let { if (it.isNotBlank()) text += "${view?.getCommentTitle()}: \n$it\n" }

        text += "\n--\n${view?.getSignString()}\n"

        return text
    }
}