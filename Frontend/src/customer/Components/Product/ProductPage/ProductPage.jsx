import React from "react";
import { productdata } from "../../../data";
import ProductCard from "../ProductCard/ProductCard";
import { Link } from "react-router-dom";

const ProductPage = () => {
  return (
    <div className="px-10 -z-10">
      <div className="flex justify-between py-5">
        <p className="font-bold">Filter</p>
        <p>Sort</p>
      </div>
      <div className="flex justify-between">
        <div className="w-[20%] border rounded-md bg-white"></div>
        <div className="flex flex-wrap justify-between w-[78%] bg-white border p-5 rounded-md">
          {productdata.map((item) => (
            <Link to={`/product/${item.id}`} key={item.id}>
              <div className="border p-2 rounded-md">
                <img src={item.image} alt={item.name} className="w-full" />
                <p>{item.name}</p>
                <p>₹{item.price}</p>
              </div>
            </Link>
          ))}
        </div>
      </div>
    </div>
  );
};

export default ProductPage;
