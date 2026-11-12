
import { useEffect, useState } from "react";

function App() {
  const [products, setProducts] = useState([]);
  const [error, setError] = useState("");

  useEffect(() => {
    fetch("http://localhost:8080/api/products")
        .then(response => {
          if (!response.ok) {
            throw new Error("Backend request failed");
          }
          return response.json();
        })
        .then(data => setProducts(data))
        .catch(error => setError(error.message));
  }, []);

  return (
      <div style={{ padding: "30px" }}>
        <h1>My E-Commerce Store</h1>

        {error && <p>{error}</p>}

        {products.map(product => (
            <div key={product.id}>
              <h3>{product.name}</h3>
              <p>Brand: {product.brand}</p>
              <p>Price: ₹{product.price}</p>
              <p>Quantity: {product.quantity}</p>
              <hr />
            </div>
        ))}
      </div>
  );
}

export default App;
