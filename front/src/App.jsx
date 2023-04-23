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
    const [result, setResult] = useState()
    const url = "http://localhost:8080/exchanges"

    useEffect(() => {
        fetchData().then(setCodes)
    }, [])

    const {register, handleSubmit} = useForm({
        defaultValues: {
            code: 'THB',
            date: '',
            quotations: ''
        }
    });

    function handleChange(event) {
        setChoice(options[event.target.value])
        setResult(undefined)
    }

    async function onSubmit(data) {
        switch (choice.value) {
            case 0: {
                const res = await axios.get(`${url}/${data.code}/${data.date}`)
                    .catch((err) => {
                        alert(err.message);
                    })
                res && setResult(res.data)
                break
            }
            case 1: {
                const res = await axios.get(`${url}/${data.code}/minmax/${data.quotations}`)
                    .catch((err) => {
                        alert(err.message);
                    })
                res && setResult(res.data)
                break
            }
            case 2: {
                const res = await axios.get(`${url}/${data.code}/difference/${data.quotations}`)
                    .catch((err) => {
                        alert(err.message);
                    })
                res && setResult(res.data)
            }
        }
    }

    return (
        <div>
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
            {result && choice.value === 0 &&
                <>
                    <p>No: {result.no}</p>
                    <p>Effective date: {result.effectiveDate}</p>
                    <p>Average rate: {result.mid}</p>
                </>
            }
            {result && choice.value === 1 &&
                <>
                    <p>Min average rate: {result.min.mid}</p>
                    <p>Max average rate: {result.max.mid}</p>
                </>
            }
            {result && choice.value === 2 &&
                <>
                    <p>Lowest bid value: {result.bid}</p>
                    <p>Highest ask value: {result.ask}</p>
                    <p>Difference: {result.difference}</p>
                </>
            }
        </div>
    )
}

export default App

async function fetchData() {
    const data = await axios.get("http://api.nbp.pl/api/exchangerates/tables/A/")
    return data.data[0].rates.map((rate) => {
        return rate.code
    })
}