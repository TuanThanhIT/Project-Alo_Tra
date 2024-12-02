function openSidebar() {
    document.getElementById("sidebar").style.width = "250px";
    document.getElementById("overlay").style.display = "block"; // Hiển thị overlay
}

function closeSidebar() {
    document.getElementById("sidebar").style.width = "0";
    document.getElementById("overlay").style.display = "none"; // Ẩn overlay
}
//Tạo combobox chọn địa chỉ
document.addEventListener("DOMContentLoaded", function () {
    const citySelect = document.getElementById("city");
    const districtSelect = document.getElementById("district");
    const wardSelect = document.getElementById("ward");
    const addressInput = document.getElementById("address");

    if (!citySelect || !districtSelect || !wardSelect || !addressInput) {
        console.error("One or more required elements are missing in the DOM.");
        return;
    }

    const Parameter = {
        url: "https://raw.githubusercontent.com/kenzouno1/DiaGioiHanhChinhVN/master/data.json",
        method: "GET",
        responseType: "application/json",
    };

    axios(Parameter).then(function (result) {
        const data = result.data;
        renderCity(data);
    }).catch(function (error) {
        console.error("Error fetching data:", error);
    });

    function renderCity(data) {
        data.forEach(city => {
            const option = new Option(city.Name, city.Id);
            citySelect.appendChild(option);
        });

        citySelect.addEventListener("change", function () {
            districtSelect.innerHTML = '<option value="" selected>Chọn quận huyện</option>';
            wardSelect.innerHTML = '<option value="" selected>Chọn phường xã</option>';

            if (this.value !== "") {
                const selectedCity = data.find(city => city.Id === this.value);
                selectedCity.Districts.forEach(district => {
                    const option = new Option(district.Name, district.Id);
                    districtSelect.appendChild(option);
                });
            }

            updateAddress();
        });

        districtSelect.addEventListener("change", function () {
            wardSelect.innerHTML = '<option value="" selected>Chọn phường xã</option>';

            if (this.value !== "") {
                const selectedCity = data.find(city => city.Id === citySelect.value);
                const selectedDistrict = selectedCity.Districts.find(district => district.Id === this.value);

                selectedDistrict.Wards.forEach(ward => {
                    const option = new Option(ward.Name, ward.Id);
                    wardSelect.appendChild(option);
                });
            }

            updateAddress();
        });

        wardSelect.addEventListener("change", updateAddress);
    }

    function updateAddress() {
        const city = citySelect.options[citySelect.selectedIndex]?.text || "";
        const district = districtSelect.options[districtSelect.selectedIndex]?.text || "";
        const ward = wardSelect.options[wardSelect.selectedIndex]?.text || "";

        addressInput.value = `${ward}, ${district}, ${city}`.replace(/, $/, "").replace(/^, /, "");
    }
});