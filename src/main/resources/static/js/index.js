const API_KEY = '80098e889320a742485a2e8bcc9993d0'; // OpenWeatherMap API 키를 여기에 입력하세요

// 현재 날짜와 시간을 표시하는 함수
function displayCurrentDateTime() {
    const now = new Date();
    const year = now.getFullYear();
    const month = (now.getMonth() + 1).toString().padStart(2, '0');
    const date = now.getDate().toString().padStart(2, '0');
    const day = now.toLocaleString('ko-KR', { weekday: 'short' }).toUpperCase();
    const hours = now.getHours().toString().padStart(2, '0');
    const minutes = now.getMinutes().toString().padStart(2, '0');
    const seconds = now.getSeconds().toString().padStart(2, '0');
    const dateTimeString = `${year}. ${month}. ${date}. (${day}) ${hours}:${minutes}:${seconds}`;
    document.getElementById('current-datetime').textContent = dateTimeString;
}

// OpenWeatherMap에서 날씨 데이터를 가져오는 함수
async function fetchWeather(lat, lon) {
    const url = `https://api.openweathermap.org/data/2.5/weather?lat=${lat}&lon=${lon}&appid=${API_KEY}&units=metric&lang=kr`;

    try {
        const response = await fetch(url);
        if (!response.ok) {
            throw new Error('날씨 데이터를 가져오는데 실패했습니다.');
        }
        const data = await response.json();
        displayWeather(data);
    } catch (error) {
        alert('날씨 데이터를 가져오는 중 오류가 발생했습니다: ' + error.message);
    }
}

// 날씨 데이터를 페이지에 표시하는 함수
function displayWeather(data) {
    const temperature = data.main.temp;
    const place = data.name;
    const description = data.weather[0].description;
    const icon = data.weather[0].icon;
    const iconURL = `http://openweathermap.org/img/wn/${icon}@2x.png`;

    document.querySelector('.temperature').innerText = temperature + ' °C';
    document.querySelector('.place').innerText = place;
    document.querySelector('.description').innerText = description;
    document.querySelector('.icon').setAttribute('src', iconURL);
}

// 현재 위치를 가져오는 함수
function getCurrentPosition() {
    navigator.geolocation.getCurrentPosition(success, fail);
}

// getCurrentPosition의 성공 콜백 함수
function success(position) {
    const latitude = position.coords.latitude;
    const longitude = position.coords.longitude;
    fetchWeather(latitude, longitude);
}

// getCurrentPosition의 실패 콜백 함수
function fail() {
    alert('현재 위치를 받아올 수 없습니다.');
}

// 날짜, 시간 및 날씨 표시를 초기화하는 함수
function init() {
    displayCurrentDateTime();
    setInterval(displayCurrentDateTime, 1000);
    getCurrentPosition();
}

// 페이지 로드 시 init 함수 호출
window.onload = init;
