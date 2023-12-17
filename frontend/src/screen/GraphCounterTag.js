import {Bar, Chart} from "react-chartjs-2";
import { Chart as ChartJS } from 'chart.js/auto'
import styles from "../assets/styles/global.scss";


const GraphCounterTag = () => {

    const timestamp = Date.now() - 2592000000;
    let xhr = new XMLHttpRequest();
    xhr.open('GET', 'http://localhost:8080/tags?timeOffset=' + timestamp, false);
    xhr.send()
    const data = JSON.parse(xhr.responseText)

    const labels = data.data.map(value => value.label)
    const values = data.data.map(value => value.value)

    const chartData = {
        labels: labels,
        datasets: [{
            label: "Tags clicking",
            data: values
        }]
    };

    return <>
        <div>
            <Bar data={chartData} />
        </div>
    </>
}

export default GraphCounterTag;