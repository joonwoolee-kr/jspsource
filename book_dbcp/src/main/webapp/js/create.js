// 추가 클릭 시 form submit 일어나면
// description 요소를 제외한 모든 요소에 값이 있는지 확인
// code는 4자리이고 숫자여야 함
// price는 3~10자리 숫자여야 함
document.querySelector("body div:nth-child(3) form").addEventListener("submit", (e) => {
	e.preventDefault();
	
	const form = e.target

	// form 내부 요소 지정
	const code = form.querySelector("#code");
	const title = form.querySelector("#title");
	const writer = form.querySelector("#writer");
	const price = form.querySelector("#price");

	const codeRegEx = /^[0-9]{4}$/;
	const priceRegEx = /^[0-9]{3,10}$/;
	const textRegEx = /[A-Za-z가-힣0-9]+/;

	if (!codeRegEx.test(code.value)) {
		alert("도서 코드는 4자리 숫자로 입력해야 합니다.");
		return;
	}
	else if (!textRegEx.test(title.value)) {
		alert("도서명을 입력하세요");
		title.select();
		return;
	}
	else if (!textRegEx.test(writer.value)) {
		alert("도서 저자를 입력하세요");
		writer.select();
		return;
	}
	else if(!priceRegEx.test(price.value)) {
		alert("가격을 입력하세요");
		price.select();
		return;
	}
	
	form.submit();
});