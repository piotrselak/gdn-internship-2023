import Form from 'react-bootstrap/Form';
import PropTypes from "prop-types";

export default function CodeInput(props) {
    return (
        <Form.Group className="mb-5" controlId="formCurrencyCode">
            <Form.Label>Currency Code</Form.Label>
            <Form.Select {...props.register("code")}>
                {props.codes.map((code, i) => {
                    return <option key={i}>{code}</option>
                })}
            </Form.Select>
        </Form.Group>
    )
}

CodeInput.propTypes = {
    codes: PropTypes.arrayOf(PropTypes.string).isRequired,
    register: PropTypes.func.isRequired,
}