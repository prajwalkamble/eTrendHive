import React from "react";
import HomeCarousel from "../customer/Components/Carousel/HomeCarousel";
import { homeCarouselData } from "../customer/Components/Carousel/HomeCaroselData";
import HomeProductSection from "../customer/Components/Home/HomeProductSection";
import { speakers } from "../Data/speakers/Speakers";
import { mobiles } from "../Data/mobiles/Mobiles";
import { laptops } from "../Data/laptops/Laptops";
import { keyboards } from "../Data/keyboards/Keyboards";
import { controllers } from "../Data/controllers/Controllers";
import { monitors } from "../Data/monitors/Monitors";
import { mouse } from "../Data/mouse/Mouse";

const Homepage = () => {

  return (
    <div className="">
      <HomeCarousel images={homeCarouselData} />

      <div className="space-y-10 py-20">
        <HomeProductSection data={mobiles} section={"Mobiles"} />
        <HomeProductSection data={laptops} section={"Laptops"} />
        <HomeProductSection data={keyboards} section={"Keyboards"} />
        <HomeProductSection data={controllers} section={"Controllers"} />
        <HomeProductSection data={monitors} section={"Monitors"} />
        <HomeProductSection data={mouse} section={"Mouse"} />
        <HomeProductSection data={speakers} section={"Speakers"} />
      </div>

    </div>
  );
};

export default Homepage;
