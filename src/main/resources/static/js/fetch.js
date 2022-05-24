function putMessage() {
  fetch("/putMessage", {
    method: "PUT",
    mode: "cors",
    body: new FormData(document.getElementById("PutMessage"))
  })
      .then(response => response.text())
      .then(data => console.log(data));
}

function updateMessageBoard() {
  fetch("/getMessages")
      .then(response => response.json())
      .then(data => {
        let temp = "";

        for (let i in data.reverse()) {
          temp += `${data[i].id} 楼的 ${data[i].nickName} 说：${data[i].message}<br />`;
        }

        document.getElementById("MessageBoard").innerHTML = temp;
      });
}

function autoUpdateMessageBoard() {
  updateMessageBoard();
  setTimeout(autoUpdateMessageBoard, 2000);
}

autoUpdateMessageBoard()