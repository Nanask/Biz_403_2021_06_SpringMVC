document.addEventListener("DOMContentLoaded", () => {
  let modal = document.querySelector("div#modal");
  modal.querySelector("span").addEventListener("cilck", (e) => {
    modal.style.display = "none";
  });
  document.querySelector("form#book_input").addEventListener("keyDown", (e) => {
    //   keyDown은 키보드의 값을 입력했을 때 발생하는 이벤트
    let key = e.key;
    let tagName = e.target.tagName;
    let id = e.target.id;

    if (key === "Enter" && target === "INPUT") {
      let text = e.target.Value;
      if (id === "bk_ccode") {
        fetch(`${rootPath}/company/list`).then((result) => {
          return result.text();
        });
        // document.createElement("div");
        // div.innerHTML = result;
        // modal.appendChild("div");
        // });
        // ajax 찾아보자
        // fetch는 비동기방식을 사용할 수 있는 것?
        // alert("출판사 찾기" + text);
        modal.style.display = "block";
      } else if (id === "bk_acode") {
        modal.style.display = "block";
        // alert("저자찾기" + text);
      }
    }

    // 도서정보의 출판사 코드에 키보드를 누르면 해당 값이 alert로 뜨게 함
    // alert(key);
  });
});
