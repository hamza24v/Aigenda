import React from "react";
import { motion } from "framer-motion"



function NotFound() {


  return (
    // <div className="flex items-center justify-center h-screen">
    //   <span className="text-center font-semibold text-5xl text-red-500">
    //     Page Not Found
    //   </span>
    // </div>
    <div className="flex items-center justify-center h-screen overflow-hidden"> {/* Ensures no overflow */}
      <motion.div
        style={{ transformOrigin: "center" }}  // Locks scaling to the center of the element
        animate={{ scale: [1, 2, 1] }}
        transition={{ duration: 6, repeat: Infinity, repeatType: "reverse" }}
      >
        <img className=" z-[-20]" width={300} height={300} src="/deadfile.jpg"/>
        <p className="text-center font-semibold text-5xl text-red-500">
          Page Not Found
        </p>
        
      </motion.div>
    </div>
  );
}

export default NotFound;
