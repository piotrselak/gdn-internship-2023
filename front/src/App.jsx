import {useEffect, useState} from 'react'
import axios from "axios";
import Form from 'react-bootstrap/Form';

import 'bootstrap/dist/css/bootstrap.min.css';
import CodeInput from "./components/CodeInput.jsx";
import InputChoice from "./components/InputChoice.jsx";

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

    function handleChange(event) {
        setChoice(options[event.target.value])
    }

    return (
        <>
            <div>
                <select value={choice.value} onChange={handleChange}>
                    {options.map((option) => (
                        <option key={option.value} value={option.value}>{option.label}</option>
                    ))}
                </select>
            </div>
            <Form>
                <CodeInput codes={codes}/>
                <InputChoice value={choice.value}/>
            </Form>
        </>
    )
}

export default App
