import React, { useState, useEffect, useMemo } from "react";
import "./homepage.styles.scss";
import CustomButton from '../../components/custom-button/custom-button.components'
import CustomInput from '../../components/command-input/command-input.components'
import { ActionList } from "../../components/actions/actions.component";
import east from '../../assets/east.svg';
import west from '../../assets/west.svg';
import north from '../../assets/north.svg';
import south from '../../assets/south.svg';


const HomePage = () => {

    const [script, setScript] = useState("");
    // eslint-disable-next-line
    const [x, setX] = useState(5);
    // eslint-disable-next-line
    const [y, setY] = useState(5);
    const [final, setFinal] = useState(null);
    // eslint-disable-next-line
    const [actions, setActions] = useState([]);
    const [tableContent, setTableContent] = useState([]);
    const [errorMessage, setErrorMessage] = useState("");

    useEffect(() => {
        if (!final) {
            setTableContent(createTable(0, 0, "EAST"));
        } else {
            setTableContent(createTable(final.x, final.y, final.direction));
        }
    }, [final]);

    const handleChange = event => {
        // eslint-disable-next-line
        const { value, name } = event.target;
        setScript(value);
    };

    const renderPosition = (script, x, y) => {
        const request = JSON.stringify({
            "script": script,
            "x": x,
            "y": y
        });
        fetch('http://localhost:8080/api/positions', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: request,
        })
            .then(response => response.json())
            .then(data => {
                if (data.status) {
                    console.error('Error:', data);
                    setFinal(null);
                    setActions(null);
                    setErrorMessage(data.message);
                } else {
                    console.log('Success:', JSON.stringify(data));
                    setFinal(data.final);
                    setActions(data.actions);
                    setErrorMessage("");
                }
            })
            .catch((error) => {
                console.error('Error:', error);
                setErrorMessage("Something went wrong : " + error)
            });
    }

    const handleSubmit = async event => {
        event.preventDefault();
        try {
            renderPosition(script, x, y);
        } catch (error) {
            console.error("Position service throws error", error.message);
            setErrorMessage("Something went wrong : " + error)
        }
    };


    const createTable = (a, b, direction) => {
        const table = []
        let indexId = 0;
        for (let row = 0; row <= 5; row++) {
            const children = []
            for (let column = 0; column <= 5; column++) {
                if (column === 0 && row === 0) {
                    children.push(<td className="tdAxis" key={indexId}></td>)
                } else if (column === 0 && row >= 1) {
                    children.push(<td className="tdAxis" key={indexId}>{row - 1}</td>)
                } else if (row === 0 && column >= 1) {
                    children.push(<td className="tdAxis" key={indexId}>{column - 1}</td>)
                }
                else {
                    if (column === a + 1 && row === b + 1) {
                        children.push(<td className="td1" key={indexId}><img src={robotDirection(direction)} alt="Robot" /></td>)
                    } else {
                        children.push(<td className="td" key={indexId}>{` `}</td>)
                    }
                }
                ++indexId;
            }
            table.push(<tr key={row}>{children}</tr>)
        }
        return table
    }

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

    const actionList = useMemo(() => {
        if (!actions) return []
        return actions;
      }, [actions]);

    return (
        <div className="home-page">
            <div className="home-page-group">
                <div className="main-container">
                    <h2 className="title">Move Mini Robot</h2>
                    <span className="title">Each line will be a new command. Let's move! </span>
                    <span className="errorMessage">{errorMessage}</span>
                    <form className="command-ui-form" onSubmit={handleSubmit}>
                        <CustomInput name="script"
                            type="text"
                            label="Command"
                            value={script}
                            handleChange={handleChange}
                            rows="12"
                            cols="30"
                            placeholder="Write your command in here"
                            required />
                        <CustomButton type="submit">Move</CustomButton>
                    </form>
                </div>
                <div className="main-container">
                    <h2 className="title">Robot Canvas</h2>
                    <table>
                        <tbody>
                            {tableContent}
                        </tbody>
                    </table>
                </div>
            </div>
            <div className="home-page-group">
                <div className="group-container">
                    <h2 className="title">Robot Journey</h2>
                    <ActionList actions={actionList}></ActionList>
                </div>
            </div>
        </div>
    )

};

export default HomePage;