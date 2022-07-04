var listToDo = [];
var list = document.getElementById("list");

function display(ele) {
    list.innerHTML += '<li style="background-color: #F5F5F5;border: none;" class="list-group-item d-flex justify-content-between align-items-center custom-height"><div style="font-size: 1.5rem;width: 80%;" class="d-flex justify-content-between"><div class="fw-bold"><span class="span">' + ele + '</span></div></div><div class="icon-wrap"><i class="fas fa-edit custom-icon" onclick="edit()"></i><i class="fas fa-trash-alt custom-icon" onclick="remove()"></i></div></li>';
}

function load() {
    listToDo = JSON.parse(localStorage.getItem('list-to-do')) === null ? [] : JSON.parse(localStorage.getItem('list-to-do'));
    listToDo.forEach(ele => {
        display(ele);
    });
}

const btnAdd = document.getElementById("btn-fake-add");

function clicked() {
    let text = document.getElementById("todo-value");
    if (text.value == "") {
        document.getElementById("message").style.visibility = "unset";
        return;
    } else {
        document.getElementById("message").style.visibility = "hidden";
    }
    listToDo.push(text.value);
    list.innerHTML = "";
    listToDo.forEach(ele => {
        display(ele);
    });
    text.value = '';
    localStorage.setItem('list-to-do', JSON.stringify(listToDo));
}
btnAdd.addEventListener('click', clicked);


function edit() {
    let index = -1;
    $("ul li").click(function() {
        btnAdd.removeEventListener('click', clicked);
        index = $(this).index();
        console.log(index);
        document.getElementById("todo-value").value = document.getElementsByClassName("span").item(index).textContent;
        document.getElementById("basic-addon2").textContent = "Update";
        document.getElementById("btn-fake-add").id = "btn-fake-update";
        const btnUpdate = document.getElementById("btn-fake-update");
        const event = function() {
            listToDo[index] = document.getElementById("todo-value").value;
            document.getElementById("basic-addon2").textContent = "Add Item";
            document.getElementById("btn-fake-update").id = "btn-fake-add";
            list.innerHTML = "";
            listToDo.forEach(ele => {
                display(ele);
            });
            document.getElementById("todo-value").value = '';
            btnUpdate.removeEventListener('click', event);
            btnAdd.addEventListener('click', clicked);
            localStorage.clear();
            localStorage.setItem('list-to-do', JSON.stringify(listToDo));
        };
        btnUpdate.addEventListener('click', event);
    });
}

function remove() {
    let index = -1;
    $("ul li").click(function() {
        index = $(this).index();
        listToDo.splice(index, 1);
        list.innerHTML = "";
        listToDo.forEach(ele => {
            display(ele);
        });
        localStorage.clear();
        localStorage.setItem('list-to-do', JSON.stringify(listToDo));
    });
}

const btnClear = document.getElementById("clear");
btnClear.addEventListener('click', () => {
    localStorage.clear();
    listToDo.length = 0;
    list.innerHTML = "";
    listToDo.forEach(ele => {
        display(ele);
    });
    document.getElementById("message").style.visibility = "hidden";
})