import React from "react";
import './action.styles.scss'
import east from '../../assets/east.svg';
import west from '../../assets/west.svg';
import north from '../../assets/north.svg';
import south from '../../assets/south.svg';

export const Action = props => {
    const validResult = props.action.valid;
    const robotDirection = (direction) => {
        switch (direction) {
            case "WEST":
                return west;
            case "NORTH":
                return north;
            case "SOUTH":
                return south;
            default:
                return east;
        }
    }
    return (
        <tr className="tr">
            <td className="td">{props.action.action}</td>
            <td className="td" >( {props.action.position.x} , {props.action.position.y})</td>
            <td className="td" ><img src={robotDirection(props.action.position.direction)} alt="Robot" /></td>
            <td className={`${validResult ? 'valid' : 'inValid'}`} />
        </tr>
    );
};