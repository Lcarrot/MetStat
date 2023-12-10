const event_array = [];
const element_array = [];

function addStatWriter() {
    var items = document.getElementsByTagName('body')[0].childNodes;
    for (var i = 0; i < items.length; i++) {
        var item = items[i];
        getRecursiveNodes(item);
    }
}

function getRecursiveNodes(element) {
    addClickEvent(element);
    for (var i = 0; i < element.childNodes.length; i++) {
        var child = element.childNodes[i];
        getRecursiveNodes(child);
    }
}

function addClickEvent(element) {
    element.addEventListener("click", (e) => {
        const time = e.timeStamp;
        if (!(time in event_array)) {
            addEvent(e, element);
        } else {
            const current_val = element_array[time];
            if (element_array[time].contains(element)) {
               addEvent(e, element);
            }
        }
    });
}

function addEvent(event, element) {
    event_array[time] = event;
    element_array[time] = element;
}

function sendToServer(event) {
    console.log('click');
}

window.addEventListener('beforeunload' ,() => {
    event_array.forEach((timeStamp, source_event) => {
        sendToServer(source_event);
        console.log('time: ' + timeStamp + ' elem: ' +source_event.target);
    });
    return true;
});

addStatWriter();
