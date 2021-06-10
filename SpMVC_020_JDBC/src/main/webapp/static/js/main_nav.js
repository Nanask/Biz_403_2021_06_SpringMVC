document.addEventListener("DOMContentLoaded", () => {
  const nav = document.querySelector("nav#main_nav");

  nav.addEventListener("click", (e) => {
    let tagName = e.target.tagName;

    if (tagName === "LI") {
      let menuText = e.target.textContent;
      //   `` =>backTit : 역 작은 따옴표
      // JS에서 변수를 포함하는 문자열을 생성할 때 사용한다.,
      let urlPath = `${rootPath}`;
      // alert(menuText);
      if (menuText === "HOME") {
        // urlPath += rootPath + "/"
        urlPath += "/";
        // location.href = "/";
      } else if (menuText === "도서정보") {
        urlPath += "/books";
      } else if (menuText === "출판사정보") {
        urlPath += "/company";
      } else if (menuText === "저자정보") {
        urlPath += "/author";
      } else if (menuText === "로그인") {
        urlPath += "/member/login";
      } else if (menuText === "회원가입") {
        urlPath += "/member/join";
      }
      //   alert("내가 가야할 곳 ${urlPath}");
      // alert(`내가 가야할 곳 ${urlPath}`);
      location.href = urlPath;
    }
  });
});
