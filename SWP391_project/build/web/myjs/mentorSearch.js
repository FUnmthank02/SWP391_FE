        
const scrollBtn = document.querySelector('#myBtn')
const btn_seemore = document.getElementById('btn_seemore')
const seemorepart = document.querySelector('.seemore')
const listItemMentorCard = document.getElementsByClassName('card-mentor')

//handle scroll
window.onscroll = () => {
    scrollFunction()
}

// scroll
const scrollFunction = () => {
    if (document.documentElement.scrollTop > 20) {
        scrollBtn.classList.add('active')
    } else {
        scrollBtn.classList.remove('active')
    }
}

// scroll back to top
const topFunction = () => {
    document.body.scrollTop = 0;
    document.documentElement.scrollTop = 0;
}


// display list item of technologies as default
const defaultDisplayItemMentor = () => {
    if (listItemMentorCard.length > 8) {
        seemorepart.classList.remove('disable')
        for (let i = 8; i < listItemMentorCard.length; i++) {
            listItemMentorCard[i].classList.add('disable')
        }
    } else {
        seemorepart.classList.add('disable')
    }
}

defaultDisplayItemMentor()

// handle load more items and see less items
const handleDisplayItemMentor = () => {
    if (listItemMentorCard.length > 8) {
        for (let i = 8; i < listItemMentorCard.length; i++) {
            listItemMentorCard[i].classList.toggle('disable')
        }
        if (listItemMentorCard[listItemMentorCard.length - 1].classList.contains('disable')) {
            btn_seemore.innerHTML = 'See All &#9207;'

        } else {
            btn_seemore.innerHTML = 'See Less &#9206;'
        }
    }
}