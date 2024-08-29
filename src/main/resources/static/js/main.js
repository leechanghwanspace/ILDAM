// JavaScript로 이미지 슬라이드를 동적으로 추가합니다.
const slider = document.getElementById('slider');
const images = 10;
const repetitions = 2;

for (let i = 0; i < repetitions; i++) {
    for (let j = 1; j <= images; j++) {
        const img = document.createElement('img');
        img.src = `/image/slide${j}.jpg`;
        img.alt = `Slide ${j}`;
        slider.appendChild(img);
    }
}
