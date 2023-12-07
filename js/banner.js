// модуль для смены изображения в баннере по таймеру

import "../css/banner.css";

// указывается блок, в котором будет баннер
// блок должен содержать изображения
function myBanner(root) {
    console.info("Loaded");

    // получение всех изображений внутри баннера
    const banners = document.querySelectorAll(`${root} img`);

    // всем изображениям устанавливается класс banner-hide
    // если были другие классы, то они будут удалены
    for (let i = 0; i < banners.length; i += 1) {
        banners[i].setAttribute("class", "banner-hide");
    }

    let old = banners.length - 1;
    let current = 0;

    // функция меняет изображения в цикле
    // изображение с классом banner-show будет показано
    // изображение с классом banner-hide будет скрыто
    // функция запускает таймер, который через 5 секунд
    // запускает функцию, снова создается таймер и т. д.
    function loop() {
        banners[current].setAttribute("class", "banner-show");
        banners[old].setAttribute("class", "banner-hide");

        console.info("Banner changed");

        old = current;
        current += 1;

        if (current === banners.length) {
            current = 0;
        }

        setTimeout(loop, 5000);
    }

    loop();
}

export default myBanner;
