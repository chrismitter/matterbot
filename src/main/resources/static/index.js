const searchGiphy = async () => {
    var search = document.getElementById('mattermost-text').value;
    const response = await fetch('/call/giphy/search2?query=' + search);
    const json = await response.json();
    json.forEach(preview);
    document.getElementById('mattermost-text').textContent = "";
};

function postToMattermost(index) {
    let text = document.getElementById('mattermost-text').value;
    text += "\n";
    text += document.getElementById('image_' + index).getAttribute('src');

    const Http = new XMLHttpRequest();
    const url = '/call?text=' + encodeURI(text);
    Http.open("GET", url);
    Http.send();

    Http.onreadystatechange = (e) => {
        console.log(Http.responseText)
    };

}

document.getElementById('mattermost-button').addEventListener('click', searchGiphy);
document.getElementById('mattermost-text').addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
        return searchGiphy();
    }
});

function preview(element, index) {
    let width = window.innerWidth / 5.1;
    document.getElementById('output').innerHTML +=
        `<img id='image_${index}' src='${element}' alt="gif" width="${width}" onclick="postToMattermost(${index});">`;
}

