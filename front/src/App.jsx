import {useEffect, useState} from 'react'
import axios from "axios";
import Form from 'react-bootstrap/Form';

import 'bootstrap/dist/css/bootstrap.min.css';
import CodeInput from "./components/CodeInput.jsx";
import InputChoice from "./components/InputChoice.jsx";
import {Button, Col, Row} from "react-bootstrap";
import {useForm} from "react-hook-form";

function App() {
    const options = [
        {label: "Get average exchange rate for a specific date", value: 0},
        {label: "Get max and min average value", value: 1},
        {label: "Get the major difference between the buy and ask rate", value: 2}]

    const [choice, setChoice] = useState(options[0])
    const [codes, setCodes] = useState([])
    useEffect(() => {
        async function fetchData() {
            const data = await axios.get("http://api.nbp.pl/api/exchangerates/tables/A/")
            const rates = data.data[0].rates.map((rate) => {
                return rate.code
            });
            return rates
        }

        fetchData().then((rates) => {
            setCodes(rates)
        })
    }, [])

    const {register, handleSubmit} = useForm({
        defaultValues: {
            code: '',
            date: '',
            quotations: ''
        }
    });

    function handleChange(event) {
        setChoice(options[event.target.value])
    }

    function onSubmit(data) {
        console.log(data)
    }

    return (
        <Form onSubmit={handleSubmit(onSubmit)}>
            <Row>
                <Col>
                    <Form.Group className="mb-5" controlId="formChoice">
                        <Form.Label>Choose option</Form.Label>
                        <Form.Select aria-label="" value={choice.value} onChange={handleChange}>
                            {options.map((option) => (
                                <option key={option.value} value={option.value}>{option.label}</option>
                            ))}
                        </Form.Select>
                    </Form.Group>
                </Col>
                <Col><CodeInput register={register} codes={codes}/></Col>
                <Col><InputChoice register={register} value={choice.value}/></Col>
                <Col>
                    <Form.Group className="mb-5" controlId="formSubmit">
                        <br/>
                        <Button type="submit">Submit</Button>
                    </Form.Group>
                </Col>
            </Row>
        </Form>
    )
}

export default App
