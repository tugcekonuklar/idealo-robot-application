import React from "react";
import "./command-input.styles.scss";


const CustomInput = ({ handleChange, placeHolder, ...otherProps }) => (
    <div className='group'>
        <textarea className='custom-input' placeholder={placeHolder} onChange={handleChange} {...otherProps} />
    </div>
);

export default CustomInput;
