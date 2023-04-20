function copyText(i) {
    var copyText = document.getElementById("copyInput" + i);
    setTimeout(async () => console.log(
        await window.navigator.clipboard.writeText(copyText.value)), 500)
}