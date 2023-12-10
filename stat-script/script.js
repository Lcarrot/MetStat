const server_path   = "http://localhost:8080/addEvents";

var event_array   = [];
var element_array = [];

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
            addEvent(e, element, time);
        } else {
            const current_val = element_array[time];
            if (element_array[time].contains(element)) {
               addEvent(e, element, time);
            }
        }
    });
}

function addEvent(event, element, time) {
    event_array[time] = event;
    element_array[time] = element;
}

function sendToServer(sendEvents) {
    const body = JSON.stringify({'events': sendEvents});
    fetch(server_path, {
        method: "POST",
        body: body,
        headers: {"Content-type": "application/json; charset=UTF-8"}
    });
}

window.addEventListener('beforeunload' ,() => {
    var sendEvents = [];
    
    Object.keys(event_array).forEach((time_stamp) => {
        source_event = event_array[time_stamp];
        console.log('time: ' + time_stamp + ' elem: ' +source_event.target);
        sendEvents.push({
            'page':  window.location.pathname,
            'tagName': source_event.target.tagName,
            'className': source_event.target.className,
            'textContent': source_event.target.textContent,
            'timestamp': Date.now()
        });
    });
    sendToServer(sendEvents);
    return true;
});

addStatWriter();
