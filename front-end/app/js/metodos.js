function campoNumerico(event) {
    var charCode = (event.which) ? event.which : event.keyCode;

    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
        return false;
    }
    else {
        return true;
    }
};

function campoCpf(event) {
    $(event).mask('000.000.000-00');
}