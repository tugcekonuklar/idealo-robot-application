import React from "react";
import "./command-input.styles.scss";


const FormInput = ({ handleChange, placeHolder, ...otherProps }) => (
    <div className='group'>
        <textarea className='form-input' placeholder={placeHolder} onChange={handleChange} {...otherProps} />
    </div>
);

export default FormInput;
