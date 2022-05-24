function putMessage() {
  const xhr = new XMLHttpRequest();
  xhr.onload = (r) => console.log(r.target.responseText);
  xhr.open("PUT", "/putMessage");
  xhr.setRequestHeader("Access-Control-Allow-Origin", "*");
  xhr.send(new FormData(document.getElementById("PutMessage")));
  // console.log(xhr.response.target);
}

function getMessages() {
  let messages;
  const xhr = new XMLHttpRequest();
  xhr.onload = (r) => messages = JSON.parse(r.target.response);
  xhr.open("GET", "/getMessages", false);
  xhr.send();
  // console.log(messages);
  return messages;
}

function updateBoardMessage() {
  const messages = getMessages();
  let temp = "";

  for (let i in messages.reverse()) {
    temp += `${messages[i].id} 楼的 ${messages[i].nickName} 说：${messages[i].message}<br />`;
  }

  document.getElementById("MessageBoard").innerHTML = temp;
}

function autoUpdateBoardMessage() {
  updateBoardMessage();
  setTimeout(() => autoUpdateBoardMessage(), 2000);
}

autoUpdateBoardMessage();



