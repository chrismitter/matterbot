const searchGiphy = async () => {
    var search = document.getElementById('matterbot-text').value;
    document.getElementById('matterbot-text').value = "";
    const response = await fetch('/call/giphy/search2?query=' + search);
    const json = await response.json();
    document.getElementById('output').innerHTML = "";
    json.forEach(preview);
};

const postAscii = async () => {
    document.getElementById('matterbot-text').value = "";
    document.getElementById('output').innerHTML = "";
    var search = document.getElementById('matterbot-text').value;
    await fetch('/call/ascii/search?query=' + search);
};

function postToMattermost(index) {
    let text = document.getElementById('giphy-text').value;
    text += "\n";
    text += document.getElementById('image_' + index).getAttribute('src');

    const Http = new XMLHttpRequest();
    const url = '/call?text=' + encodeURI(text);
    Http.open("GET", url);
    Http.send();

    Http.onreadystatechange = () => {
        console.log(Http.responseText)
    };

}

document.getElementById('giphy-button').addEventListener('click', searchGiphy);
document.getElementById('ascii-button').addEventListener('click', postAscii);

function preview(element, index) {
    let width = window.innerWidth / 5.1;
    document.getElementById('output').innerHTML +=
        `<img id='image_${index}' src='${element}' alt="gif" width="${width}" onclick="postToMattermost(${index});">`;
}

