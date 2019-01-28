import React, { Component } from "react";
import "./App.css";
import FrontImg from "./FrontImg";
import StarterPokemon from "./StarterPokemon";
import BuyMenu from "./BuyMenu";
import SellMenu from "./SellMenu";

import {
  Navbar,
  NavbarBrand,
  NavItem,
  NavLink,
  Col,
  Row,
  Container,
  Input,
  TabContent,
  TabPane,
  Nav,
  Button,
  FormGroup
} from "reactstrap";

export default class Shop extends Component {
  constructor(props) {
    super(props);
    this.buyToggle = this.buyToggle.bind(this);
    this.sellToggle = this.sellToggle.bind(this);
   ;

    this.state = {
      buy: false,
      sell: false
    };
  }
  buyToggle() {
    this.setState(prevState => ({
      buy: !prevState.buy
    }));
  }
  sellToggle() {
    this.setState(prevState => ({
      sell: !prevState.sell
    }));
  }

  render() {
    const buy = this.state.buy;
    const sell = this.state.sell;

    if (buy) {
      return (
        <div className="col-game">
          <BuyMenu close={this.buyToggle} />
        </div>
      );
    }

    if (sell) {
      return (
        <div className="col-game">
          <SellMenu close={this.sellToggle} />
        </div>
      );
    }

    return (
      <div className="col-game">
        <Container className="menu main-game-panel">
          <button className=" main-game-panel" onClick={this.buyToggle}>
            Buy
          </button>
          <button className=" main-game-panel" onClick={this.sellToggle}>
            Sell
          </button>
          <button className=" main-game-panel" onClick={this.props.close}>
            Leave
          </button>
        </Container>
      </div>
    );
  }
}
