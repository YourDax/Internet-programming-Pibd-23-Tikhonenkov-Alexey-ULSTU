import { useEffect, useState } from 'react';
import banner1 from '../../assets/1.jpg';
import banner2 from '../../assets/2.jpg';
import banner3 from '../../assets/3.jpg';
import './Banner.css';

const Banner = () => {
    const [currentBanner, setCurrentBanner] = useState(0);
    const banners = [banner1, banner2, banner3];

    const getBannerClass = (index) => {
        return currentBanner === index ? 'banner-show' : 'banner-hide';
    };

    useEffect(() => {
        const bannerInterval = setInterval(
            () => {
                console.info('Banner changed');
                let current = currentBanner + 1;
                if (current === banners.length) {
                    current = 0;
                }
                setCurrentBanner(current);
            },
            5000,
        );
        return () => clearInterval(bannerInterval);
    });

    return (
        <div id="banner" >
            {
                banners.map((banner, index) =>
                    <img key={index} className={getBannerClass(index)} src={banner} alt={`banner${index}`} />)
            }
        </div >
    );
};

export default Banner;
