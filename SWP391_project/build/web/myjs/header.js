const bar = document.getElementById('bar-container')
const cate1 = document.querySelector('.cate1')
const subcate1 = document.querySelector('.firstSubCategory')
const updown = document.querySelector('.up_down_icon')

// open the bar
const openBar = () => {
    bar.classList.toggle('active');
    if (bar.classList.contains('active'))
        updown.innerHTML = 'Find mentors &#9206;'
    else updown.innerHTML = 'Find mentors &#9207;';
}

// open subcategory1
const openCate1 = () => {
    subcate1.classList.add('active');
    cate1.classList.add('bg_active');
}

