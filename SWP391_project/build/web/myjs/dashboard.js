const block_item_sidebar = document.querySelectorAll('.block-item-sidebar')
const contain_statistic = document.querySelector('.contain-statistic')
const contain_mentee = document.querySelector('.contain-mentee')
const contain_mentor = document.querySelector('.contain-mentor')

$(window).on("load resize ", function () {
  var scrollWidth = $('.tbl-content').width() - $('.tbl-content table').width();
  $('.tbl-header').css({ 'padding-right': scrollWidth });
}).resize();

const handleShow = (element) => {
  for (let i = 0; i < block_item_sidebar.length; i++) {
    block_item_sidebar[i].classList.remove('active')
  }
  element.classList.add('active')

  for (let i = 0; i < block_item_sidebar.length; i++) {
    if (block_item_sidebar[i].classList.contains('active')) {
      switch (i) {
        case 0:
          contain_statistic.classList.add('show')
          contain_mentor.classList.remove('show')
          contain_mentee.classList.remove('show')
          break;
        case 1:
          contain_statistic.classList.remove('show')
          contain_mentor.classList.add('show')
          contain_mentee.classList.remove('show')
          break;
        case 2:
          contain_statistic.classList.remove('show')
          contain_mentor.classList.remove('show')
          contain_mentee.classList.add('show')
          break;
      }
      break;
    }
  }
}

