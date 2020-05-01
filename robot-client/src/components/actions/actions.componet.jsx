
import React from "react";
import "./actions.styles.scss";
import { Action } from "../action/action.component";

export const ActionList = props => {
    const renderTableHeader = (actions) => {
        let header = ["Action", "Position", "Direction", "Result"];
        return header.map((key, index) => {
            return <th key={index} className="header">{key.toUpperCase()}</th>
        })
    }
    return (
        <div className="actions-main-container">
            <table className="actions-table">
                <tbody>
                    <tr>{renderTableHeader(props.actions)}</tr>
                    {props.actions.map(action => (
                        <Action key={action.uniqueId} action={action}></Action>
                    ))}
                </tbody>
            </table>
        </div>
    );
};