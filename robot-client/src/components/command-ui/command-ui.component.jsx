import React from "react";
import "./command-ui.styles.scss";
import CustomButton from '../custom-button/custom-button.components'
import CustomInput from '../command-input/command-input.components'

class CommandUi extends React.Component {
    constructor() {
        super();

        this.state = {
            script: "",
            x: 5,
            y: 5
        };
    }
    handleChange = event => {
        const { value, name } = event.target;
        this.setState({ [name]: value });
    };

    renderPosition = () => {
        console.log(JSON.stringify(this.state));
        fetch('http://localhost:8080/api/positions', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(this.state),
        })
        .then(response => response.json())
        .then(data => {
          console.log('Success:', data);
        })
        .catch((error) => {
          console.error('Error:', error);
        });
    }

    handleSubmit = async event => {
        event.preventDefault();
        try {
            this.renderPosition();
        } catch (error) {
            console.error("Position service throws error", error.message);
            alert(error);
        }
    };

    render() {
        const { script } = this.state;
        return (
            <div className="command-ui">
                <h2 className="title">Move Mini Robot</h2>
                <span>You can move your robot by writing commands</span>
                <form className="command-ui-form" onSubmit={this.handleSubmit}>
                    <CustomInput name="script"
                        type="text"
                        label="Command"
                        value={script}
                        handleChange={this.handleChange}
                        rows="20"
                        cols="50"
                        placeholder="Write your command here"
                        required />
                    <CustomButton type="submit">Move</CustomButton>
                </form>

            </div>
        );
    };
};

export default CommandUi;
