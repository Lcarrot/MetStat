function addStatWriter() {
    var items = document.getElementsByTagName('body')[0].childNodes;
    for (var i = 0; i < items.length; i++) {
        var item = items[i];
        getRecursiveNodes(item);
    }
}

function getRecursiveNodes(element) {
    if (element.childNodes.length === 0) {
        addClickEvent(element);
        return;
    }
    for (var i = 0; i < element.childNodes.length; i++) {
        var child = element.childNodes[i];
        addClickEvent(element);
        getRecursiveNodes(child);
    }
}

function addClickEvent(element) {
    console.log(element);
    element.addEventListener("click", function(e) {
        console.log('click');
    });
}

addStatWriter();