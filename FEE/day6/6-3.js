function btnShowCake(type) {
    if (type === "all") {
        var card = document.getElementsByClassName("card");
        for (var i = 0; i < card.length; i++) {
            showCake(card[i].attributes.name.nodeValue);
        };
    } else {
        var card = document.getElementsByClassName("card");
        for (var i = 0; i < card.length; i++) {
            if (card[i].attributes.name.nodeValue !== type)
                hideCake(card[i].attributes.name.nodeValue);
        };
        showCake(type);
    }

}

function hideCake(type) {
    var c_type = document.getElementsByName(type);
    c_type.forEach(e => {
        e.setAttribute("class", "card d-none");
    });
}
function showCake(type) {
    var c_type = document.getElementsByName(type);
    c_type.forEach(element => {
        element.removeAttribute("class");
        element.setAttribute("class", "card");
    });
}

function cakeSearch() {
    var allCard = document.getElementsByClassName("card");
    var searchValue = document.getElementById("search_ip").value;
    var showList = [];
    var hideList = [];

    for (let index = 0; index < allCard.length; index++) {
        var text = searchValue;
        var Utext = text.charAt(0).toUpperCase() + text.slice(1).toLowerCase();
        if (allCard[index].innerText.search(Utext) !== -1)
            showList.push(allCard[index].attributes.name.nodeValue);
        if (allCard[index].innerText.search(Utext) === -1)
            hideList.push(allCard[index].attributes.name.nodeValue);
    }

    for (let index = 0; index < showList.length; index++) {
        showCake(showList[index]);
    }

    for (let index = 0; index < hideList.length; index++) {
        hideCake(hideList[index]);
    }

}