document.addEventListener("DOMContentLoaded", () => {
  let modal = document.querySelector("div#modal");
  modal.querySelector("span").addEventListener("click", (e) => {
    // 임의로 생성된 div_search Box는
    // modal과 별개로 생성을 하였으므로 div_search box를 remove하고 modal을 감춘다.
    document.querySelector("div#div_search").remove();
    modal.style.display = "none";
  });
  document.querySelector("form#book_input").addEventListener("keydown", (e) => {
    //   keyDown은 키보드의 값을 입력했을 때 발생하는 이벤트
    let key = e.key;
    let tagName = e.target.tagName;
    let id = e.target.id;
    let className = e.target.className;

    if (key === "Enter" && target === "INPUT") {
      let text = e.target.Value;
      let urlPath = rootPath;

      if (id === "bk_ccode") {
        urlPath += `/company/search?cp_title=${text}`;
      } else if (id === "bk_acode") {
        urlPath += `/author/search?au_name=${text}`;
      }
      if (className === "search") {
        modal.style.display = "block";
        fetch(urlPath)
          .then((res) => {
            return res.text();
          })
          .then((result) => {
            // 새로운 element(tag)
            let div = document.createElement("div");
            // 본문 내용 추가
            div.innerHTML = result;
            // id 추가
            div.setAttribute("id", "div_search");
            document.querySelector("body").appendChild(div);
          });
      }

      // document.createElement("div");
      // div.innerHTML = result;
      // modal.appendChild("div");
      // });
      // ajax 찾아보자
      // fetch는 비동기방식을 사용할 수 있는 것?
      // alert("출판사 찾기" + text);

      // alert("저자찾기" + text);
    }
  });
  document
    .querySelector("form#book_input button.btn_save")
    .addEventListener("click", (e) => {
      let form = document.querySelector("form#book_input");

      let bk_isbn = form.querySelector("input#bk_isbn");
      let bk_title = form.querySelector("input#bk_title");
      let bk_ccode = form.querySelector("input#bk_ccode");
      let bk_acode = form.querySelector("input#bk_acode");
      let bk_price = form.querySelector("input#bk_price");
      let bk_pages = form.querySelector("input#bk_pages");

      // front 단에서 유효성 검사

      if (bk_isbn.Value === "") {
        alert("ISBN은 반드시 입력하세요");
        bk_isbn.focus(); //값이 입력되어있지 않으면 alert가 나타나고 isbn칸에 포커싱하게 한다.
        return false; //더이상 진행하지 말라
      }
      if (bk_isbn.value.length !== 13) {
        alert("ISBN은 13자리여야 합니다.");
        bk_isbn.focus();
        return false;
      }
      if (bk_title.value === "") {
        alert("도서명은 반드시 입력하세요");
        bk_title.focus();
        return false;
      }

      // 여기 도착하면 유효성검사 통과
      // form에 담긴 데이터를 서버로 req하는데
      // front에서는 submit한다 라고 표현한다.
      form.submit();
      // 이 코드를 입력하면 서버에 데이터가 전송된다?
    });
});
