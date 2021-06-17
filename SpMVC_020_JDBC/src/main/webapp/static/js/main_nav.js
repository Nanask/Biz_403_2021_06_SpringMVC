document.addEventListener("DOMContentLoaded", () => {
  const nav = document.querySelector("nav#main_nav");

  nav.addEventListener("click", (e) => {
    let tagName = e.target.tagName;

    if (tagName === "LI") {
      let menuText = e.target.textContent;
      //   `` =>backTit : 역 작은 따옴표
      // JS에서 변수를 포함하는 문자열을 생성할 때 사용한다.,
      // let urlPath

      // ``은 js파일에서만 사용하며 각각의 메뉴를 클릭했을 때 공통으로 필요한 rootPath값을 문자열로 변수에 세팅
      let urlPath = `${rootPath}`;
      // alert(menuText);
      if (menuText === "HOME") {
        // urlPath += rootPath + "/"
        urlPath += "/";
        // location.href = "/";
      } else if (menuText === "도서정보") {
        // location.href = "{$rootPath}/books"와 같은의미
        urlPath += "/books";
      } else if (menuText === "출판사정보") {
        urlPath += "/company";
      } else if (menuText === "저자정보") {
        urlPath += "/author";
      } else if (menuText === "로그인") {
        urlPath += "/member/login";
      } else if (menuText === "회원가입") {
        urlPath += "/member/join";
      } else if (menuText === "로그아웃") {
        urlPath += "/member/logout";
      } else if (e.target.id === "mypage") {
        urlPath += "/member/id";
      }

      //   alert("내가 가야할 곳 ${urlPath}");
      // alert(`내가 가야할 곳 ${urlPath}`);
      location.href = urlPath;
    }
  });
});
