document.getElementById('search-form').addEventListener('submit', function(event) {
    event.preventDefault();
    const query = document.getElementById('search-input').value;
    searchGoogle(query);
});

function searchGoogle(query) {
    const apiKey = '############################';
    const cx = 'd65a265b8270645c1';
    const url = `https://www.googleapis.com/customsearch/v1?key=${apiKey}&cx=${cx}&q=${query}`;

    fetch(url)
        .then(response => response.json())
        .then(data => {
            displayResults(data.items);
        })
        .catch(error => console.error('Error:', error));
}

function displayResults(items) {
    const resultsDiv = document.getElementById('search-results');
    resultsDiv.innerHTML = '';

    if (!items) {
        resultsDiv.innerHTML = '<p>No results found</p>';
        return;
    }

    items.forEach(item => {
        const resultItem = document.createElement('div');
        resultItem.classList.add('result-item');

        const title = document.createElement('h3');
        const link = document.createElement('a');
        link.href = item.link;
        link.textContent = item.title;
        link.target = '_blank';
        title.appendChild(link);

        const snippet = document.createElement('p');
        snippet.textContent = item.snippet;

        resultItem.appendChild(title);
        resultItem.appendChild(snippet);
        resultsDiv.appendChild(resultItem);
    });
}
