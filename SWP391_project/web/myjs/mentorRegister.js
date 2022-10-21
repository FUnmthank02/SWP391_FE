const ckbOtherTech = document.querySelector('#ckbOtherTech')
const other_technology = document.querySelector('#other_technology')
const ckb = document.querySelectorAll('input[type=checkbox]')
const btn_submit = document.querySelector('.btn_submit')
const error_noti = document.querySelector('.error-noti')

function handleToggleDisabled() {
    if (ckbOtherTech.checked) {
        other_technology.removeAttribute('disabled')
        handleFillCkeckbox()
    } else
        other_technology.setAttribute('disabled', '')
}

const handleFillCkeckbox = () => {
    let check = false
    btn_submit.setAttribute('disabled', '')
    for (var i = 0; i < ckb.length; i++) {
        if (ckb[i].checked) {
            check = true
        }
    }
    if (check) {
        btn_submit.removeAttribute('disabled')
    }
}
