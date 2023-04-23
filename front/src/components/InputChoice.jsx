import PropTypes from "prop-types";

export default function InputChoice(props) {
    if (props.value === 0) {
        return <></>
    } else if (props.value === 1) {
        return <></>
    } else {
        return <></>
    }
}

InputChoice.propTypes = {
    value: PropTypes.number.isRequired,
}