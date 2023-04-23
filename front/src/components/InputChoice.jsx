import PropTypes from "prop-types";
import Form from "react-bootstrap/Form";

export default function InputChoice(props) {
    if (props.value === 0) {
        return <Form.Group className="mb-5" controlId="formDate">
            <Form.Label>Date</Form.Label>
            <Form.Control type="date" {...props.register("date")} required pattern="\d{4}-\d{2}-\d{2}"/>
        </Form.Group>
    }
    return <Form.Group className="mb-5" controlId="formQuotations">
        <Form.Label>Last quotations</Form.Label>
        <Form.Control {...props.register("quotations")}/>
    </Form.Group>
}

InputChoice.propTypes = {
    value: PropTypes.number.isRequired,
    register: PropTypes.func.isRequired
}