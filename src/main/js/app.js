const React = require('react');
const ReactDOM = require('react-dom');

class App extends React.Component {

    constructor(props) {
        super(props);
        this.state = {plantings: []};
    }

    componentDidMount() {
        fetch("/planting")
            .then(res => res.json())
            .then(result => this.setState({plantings: result}));
    }

    render() {
        return (
            <PlantingList plantings={this.state.plantings}/>
        )
    }
}

class PlantingList extends React.Component{
    render() {
        const plantings = this.props.plantings.map(planting =>
            <Planting key={planting.id} planting={planting}/>
        );
        return (
            <table>
                <tbody>
                    <tr>
                        <th>Lon</th>
                        <th>Lat</th>
                        <th>Planted Time</th>
                    </tr>
                    {plantings}
                </tbody>
            </table>
        )
    }
}

class Planting extends React.Component{
    render() {
        return (
            <tr>
                <td>{this.props.planting.lon}</td>
                <td>{this.props.planting.lat}</td>
                <td>{this.props.planting.plantedTime}</td>
            </tr>
        )
    }
}

ReactDOM.render(
    <App />,
    document.getElementById('react')
)