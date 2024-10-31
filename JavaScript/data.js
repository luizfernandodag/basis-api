const data = {
  "ropa": {
    "pantalon": {
      "pantalon1": {
        "january": 100,
        "february": 250,
        "march": 350,
        "april": 150
      },
      "pantalon2": {
        "january": 200,
        "february": 150,
        "march": 200,
        "april": 300
      }
    },
    "camisa": {
      "camisa1": {
        "january": 150,
        "february": 200,
        "march": 250,
        "april": 100
      },
      "camisa2": {
        "january": 250,
        "february": 100,
        "march": 150,
        "april": 200
      }
    }
  },
  "zapatos": {
    "zapato": {
      "zapato1": {
        "january": 100,
        "february": 250,
        "march": 350,
        "april": 150
      },
      "zapato2": {
        "january": 200,
        "february": 150,
        "march": 200,
        "april": 300
      }
    }
  }
};

const categorySelect = document.getElementById('categorySelect');
const productSelect = document.getElementById('productSelect');
const brandSelect = document.getElementById('brandSelect');
const chartCanvas = document.getElementById('chartCanvas');

function updateSelects(category) {
  productSelect.innerHTML = '';
  brandSelect.innerHTML = '';

  const products = Object.keys(data[category]);

  for (const product of products) {
    const option = document.createElement('option');
    option.value = product;
    option.text = product;
    productSelect.appendChild(option);
  }

  // Update brands based on the selected product
  updateBrands(category, products[0]);
}

function updateBrands(category, product) {
  brandSelect.innerHTML = '';

  const brands = Object.keys(data[category][product]);

  for (const brand of brands) {
    const option = document.createElement('option');
    option.value = brand;
    option.text = brand;
    brandSelect.appendChild(option);
  }
}

function createChart(data, category, product, brand) {
  // Assuming you're using Chart.js, you'd create a chart instance here.
  // Example using Chart.js:

  const chart = new Chart(chartCanvas, {
    type: 'line',
    data: {
      labels: ['January', 'February', 'March', 'April'],
      datasets: [{
        label: 'Sales',
        data: [data[category][product][brand]['january'], data[category][product][brand]['february'], data[category][product][brand]['march'], data[category][product][brand]['april']],
        backgroundColor: 'rgba(54, 162, 235, 0.2)',
        borderColor: 'rgba(54, 162, 235, 1)',
        borderWidth: 1
      }]
    },
    options: {
      scales: {
        y: {
          beginAtZero: true
        }
      }
    }
  });
}

// Initial setup
updateSelects('ropa');

// Event listeners
categorySelect.addEventListener('change', function() {
  updateSelects(this.value);
});

productSelect.addEventListener('change', function() {
  updateBrands(categorySelect.value, this.value);
});

brandSelect.addEventListener('change', function() {
  const category = categorySelect.value;
  const product = productSelect.value;
  const brand = this.value;
  createChart(data, category, product, brand);
});

// Initial chart
createChart(data, 'ropa', 'pantalon', 'pantalon1'); // Initial chart with default values